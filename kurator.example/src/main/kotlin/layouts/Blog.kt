package layouts

import kotlinx.html.*
import includes.Head
import includes.Include
import models.TestData
import org.codetome.kurator.extension.content
import org.codetome.kurator.extension.include
import org.codetome.kurator.template.Layout
import org.codetome.kurator.template.template

object Blog : Layout(template { ctx ->
    val (page) = ctx
    html {
        include(Head, ctx)
        body {
            h1 { text("Blog entry: ${page.title}") }
            content(page.content)
        }
    }
})
