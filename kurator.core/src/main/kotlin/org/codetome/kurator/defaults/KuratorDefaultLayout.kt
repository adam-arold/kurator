package org.codetome.kurator.defaults

import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.html
import kotlinx.html.p
import org.codetome.kurator.data.domain.TemplateContext
import org.codetome.kurator.template.Layout
import org.codetome.kurator.template.buildTemplate

object KuratorDefaultLayout : Layout<TemplateContext>(buildTemplate { ctx ->
    val (page, site) = ctx
    html {
        body {
            h1 { text("Welcome to Kurator!") }
            p {
                text("This is the default layout. Change it by setting" +
                        " the `layout` property in your `Config`.")
            }
        }
    }
})
