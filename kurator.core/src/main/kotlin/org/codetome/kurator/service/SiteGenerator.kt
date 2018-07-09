package org.codetome.kurator.service

import kotlinx.html.stream.appendHTML
import org.codetome.kurator.data.domain.*
import org.codetome.kurator.data.domain.Collection
import org.codetome.kurator.extensions.exists
import org.codetome.kurator.template.Layout
import org.codetome.kurator.template.TemplateBuilder
import org.reflections.Reflections
import java.io.File


@Suppress("UNCHECKED_CAST")
class SiteGenerator(private val documentLoader: DocumentLoader) {

    fun generate(config: Configuration) {

        val layouts = loadLayouts(config)

        // collections
        val collections = loadCollections(config)

        // site data
        val siteData = loadSiteData(config, collections)

        val destinationDir = prepareDestinationDir(config)

        generateCollections(collections, config, siteData, destinationDir)

        copyAssets(config)
    }

    private fun loadSiteData(config: Configuration, collections: List<Collection<out Any>>): Site {
        return Site(
                title = config.title,
                collections = collections,
                pages = collections.flatMap { it.pages },
                configuration = config)
    }

    private fun loadLayouts(config: Configuration): Map<String, Layout<out Any>> {
        val reflections = Reflections(config.layoutsDir)
        return reflections.getSubTypesOf(Layout::class.java).map { klass ->
            val instances = klass.declaredFields.filter { it.name == OBJECT_INSTANCE_NAME }
            require(instances.size == 1) {
                "A layout must be declared as an `object`."
            }
            val layout = instances.first().get(null) as? Layout<out Any>
                    ?: throw IllegalArgumentException("A Layout must extend `Layout`.")
            layout::class.simpleName!! to layout
        }.toMap()
    }

    private fun generateCollections(collections: List<Collection<out Any>>,
                                    config: Configuration,
                                    siteData: Site,
                                    destinationDir: File) {
        collections.forEach { coll: Collection<out Any> ->
            File(config.destinationDir + File.separator + coll.config.name).mkdirs()
            val collConfig: CollectionConfig<out Any> = config.fetchCollectionConfigFor(coll)
            coll.pages.forEach { page ->
                val contentBuilder: TemplateBuilder<Any> = coll.layout().templateBuilder as TemplateBuilder<Any>
                val sb = StringBuilder()
                val tagConsumer = sb.appendHTML()
                contentBuilder.build(tagConsumer, TemplateContext(
                        page = page,
                        site = siteData,
                        pageData = collConfig.defaultValues))

                val documentFile = File(destinationDir.absolutePath + File.separator +
                        coll.config.name + File.separator +
                        page.filename + ".html")
                documentFile.writeText(sb.toString())
            }
        }
    }

    private fun prepareDestinationDir(config: Configuration): File {
        val destinationDir = File(config.destinationDir)
        destinationDir.exists {
            it.listFiles().forEach {
                it.deleteRecursively()
            }
        }
        destinationDir.mkdirs()
        return destinationDir
    }

    private fun copyAssets(config: Configuration) {
        val assetsDir = File(config.sourceDir + File.separator + config.assetsDir)
        assetsDir.copyRecursively(File(config.destinationDir + File.separator + config.assetsDir))
    }

    private fun loadCollections(config: Configuration): List<Collection<out Any>> {
        val collectionsConfig = config.collectionsConfig.map { it.name to it }.toMap()
        val collectionsDir = File(config.collectionsDir)
        return collectionsDir.listFiles { it ->
            it.isDirectory
        }.map { collectionDir ->
            val collName = collectionDir.name
            val collConfig: CollectionConfig<out Any> = collectionsConfig[collName]
                    ?: throw NoSuchElementException("No config found for collection: '$collName'.")
            Collection(
                    config = collConfig as CollectionConfig<Any>,
                    pages = collectionDir.listFiles()
                            .filter { it.isFile }
                            .map { documentLoader.load(it, config, collConfig) })
        }
    }

    companion object {
        const val OBJECT_INSTANCE_NAME = "INSTANCE"
    }
}
