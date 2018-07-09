package includes

import kotlinx.html.head
import kotlinx.html.link
import kotlinx.html.meta
import kotlinx.html.title
import org.codetome.kurator.data.domain.TemplateContext
import org.codetome.kurator.template.Template
import org.codetome.kurator.template.buildTemplate

object Head : Template<TemplateContext>(buildTemplate { ctx ->
    head {
        title(ctx.site.title)
        link {
            href = "https://fonts.googleapis.com/css?family=Source+Code+Pro"
            rel = "stylesheet"
        }
        link {
            href = "/assets/css/main.css"
            rel = "stylesheet"
        }
        meta {
            name = "author"
            content = "Adam Arold"
        }
    }
})
