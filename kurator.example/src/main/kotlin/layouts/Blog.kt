package layouts

import includes.Head
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.html
import org.codetome.kurator.data.domain.TemplateContext
import org.codetome.kurator.extension.content
import org.codetome.kurator.extension.include
import org.codetome.kurator.template.Layout
import org.codetome.kurator.template.buildTemplate

object Blog : Layout<TemplateContext>(buildTemplate { ctx ->
    val (page) = ctx
    html {
        include(Head, ctx)
        body {
            h1 { text("Blog entry: ${page.title}") }
            content(page.content)
        }
    }
})
