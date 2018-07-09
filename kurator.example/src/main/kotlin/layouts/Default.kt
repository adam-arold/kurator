package layouts

import includes.Head
import kotlinx.html.*
import org.codetome.kurator.data.domain.TemplateContext
import org.codetome.kurator.extension.content
import org.codetome.kurator.extension.include
import org.codetome.kurator.template.Layout
import org.codetome.kurator.template.buildTemplate

object Default : Layout<TemplateContext>(buildTemplate { ctx ->
    val (page, site) = ctx
    html {
        include(Head, ctx)
        body {
            nav {
                ul {
                    li {
                        text("home")
                    }
                }
            }
            div("container") {
                h1 { text(page.title) }
                div {
                    content(page.content)
                }
            }
            footer {
                text("Copyright ${site.title}")
            }
        }
    }
})
