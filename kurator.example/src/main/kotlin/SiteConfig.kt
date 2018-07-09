import layouts.Blog
import layouts.Default
import models.BlogData
import org.codetome.kurator.data.user.Config
import org.codetome.kurator.data.user.configure

object SiteConfig : Config(configure {
    title = "Kurator example site"
    email = "email@example.com"
    description = "This is just a simple example site made with Kurator."
    url = "http://example.com"
    sourceDir = "kurator.example/src/main/resources"
    assetsDir = "assets"
    destinationDir = "site"
    collectionsDir = "collections"
    layoutsDir = "layouts"
    dataDir = "data"
    debug = false
    markdownExtensions = listOf("markdown", "md")
    host = "127.0.0.1"
    port = 4000
    baseurl = ""
    defaultLayout = Default
    collections {
        collection<BlogData> {
            name = "blog"
            permalink = "blog"
            layout = Blog
            defaultValues = BlogData(
                    author = "Batman",
                    featured = false)
        }
        collection<Unit> {
            name = "news"
            permalink = "news"
            layout = Default
            defaultValues = Unit
        }
    }
})
