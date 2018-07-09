package layouts

import includes.Head
import kotlinx.html.*
import org.codetome.kurator.data.domain.TemplateContext
import org.codetome.kurator.extensions.content
import org.codetome.kurator.extensions.include
import org.codetome.kurator.template.Layout
import org.codetome.kurator.template.template

object Default : Layout<TemplateContext<Unit>>(template { ctx ->
    val (page, site) = ctx
    html {
        include(Head, ctx.site)
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
