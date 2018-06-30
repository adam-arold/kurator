import org.codetome.kurator.service.DocumentLoader
import org.codetome.kurator.service.SiteGenerator

fun main(args: Array<String>) {
    SiteGenerator(DocumentLoader())
            .generate(SiteConfig.configuration)
}
