package org.codetome.kurator.service

import com.vladsch.flexmark.ast.Document
import com.vladsch.flexmark.ast.Heading
import com.vladsch.flexmark.ast.ThematicBreak
import com.vladsch.flexmark.ext.abbreviation.AbbreviationExtension
import com.vladsch.flexmark.ext.definition.DefinitionExtension
import com.vladsch.flexmark.ext.footnotes.FootnoteExtension
import com.vladsch.flexmark.ext.tables.TablesExtension
import com.vladsch.flexmark.ext.typographic.TypographicExtension
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.parser.ParserEmulationProfile
import com.vladsch.flexmark.util.options.MutableDataSet
import org.codetome.kurator.data.domain.CollectionConfig
import org.codetome.kurator.data.domain.Configuration
import org.codetome.kurator.data.user.UserDocumentData
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import org.yaml.snakeyaml.representer.Representer
import java.io.File
import java.util.*


class DocumentLoader {

    fun <T> load(path: String, config: Configuration, collectionConfig: CollectionConfig<T>) = load(File(path), config, collectionConfig)

    fun <T> load(file: File, config: Configuration, collectionConfig: CollectionConfig<T>): org.codetome.kurator.data.domain.Document {
        val fileContent = file.readText()
        val parser = Parser.builder(options).build()
        val (document, frontMatterStr) = fetchDocumentAndFrontMatter(parser, fileContent)
        val content = HtmlRenderer.builder(options).build().render(document)
        val userDocumentData: UserDocumentData = loadUserPageData(frontMatterStr)
        val customDocumentData: Any = collectionConfig.layout
        val id = file.nameWithoutExtension // TODO: fix this

        return org.codetome.kurator.data.domain.Document(
                title = userDocumentData.title,
                excerpt = userDocumentData.excerpt,
                date = userDocumentData.date,
                categories = userDocumentData.categories.toList(),
                tags = userDocumentData.tags.toList(),
                id = id,
                filename = file.nameWithoutExtension,
                fileExtension = file.extension,
                content = content,
                url = "${config.host}:${config.port}/${config.baseurl}/$id",
                path = id, // TODO: fix this
                previous = Optional.empty(),
                next = Optional.empty())
    }

    private fun loadUserPageData(frontMatterStr: String): UserDocumentData {
        val representer = Representer()
        representer.propertyUtils.isSkipMissingProperties = true

        return Yaml(Constructor(UserDocumentData::class.java), representer)
                .load(frontMatterStr) as UserDocumentData
    }

    private fun fetchDocumentAndFrontMatter(parser: Parser, fileContent: String): Pair<Document, String> {
        val document = parser.parse(fileContent)
        require(document.hasOrMoreChildren(2)) {
            "This document does not contain a Front Matter."
        }
        val childIter = document.children.iterator()
        val hr = childIter.next()
        require(hr is ThematicBreak) {
            "A document must tart with a thematic break (eg: '---')."
        }
        childIter.remove()
        val frontMatter = childIter.next()
        require(frontMatter is Heading && frontMatter.level == 2) {
            "This document does not have a front matter."
        }
        childIter.remove()

        var frontMatterStr = frontMatter.chars.toString()

        frontMatterStr = frontMatterStr.substring(0, frontMatterStr.length - 3)
                .trim().trimIndent()
        return Pair(document, frontMatterStr)
    }

    companion object {
        val options = MutableDataSet().apply {
            setFrom(ParserEmulationProfile.KRAMDOWN)
            set(Parser.EXTENSIONS, listOf(
                    AbbreviationExtension.create(),
                    DefinitionExtension.create(),
                    FootnoteExtension.create(),
                    TablesExtension.create(),
                    TypographicExtension.create()))
        }

    }
}
