package layouts

import kotlinx.html.*
import includes.Head
import org.codetome.kurator.extension.content
import org.codetome.kurator.extension.include
import org.codetome.kurator.template.Layout
import org.codetome.kurator.template.template

object Default : Layout(template { ctx ->
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
