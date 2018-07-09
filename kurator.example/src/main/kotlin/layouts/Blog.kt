package layouts

import includes.Head
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.html
import models.BlogData
import org.codetome.kurator.data.domain.TemplateContext
import org.codetome.kurator.extensions.content
import org.codetome.kurator.extensions.include
import org.codetome.kurator.template.Layout
import org.codetome.kurator.template.template

object Blog : Layout<TemplateContext<BlogData>>(template { ctx ->
    val (page) = ctx
    html {
        include(Head, ctx.site)
        body {
            h1 { text("Blog entry: ${page.title}") }
            content(page.content)
        }
    }
})
