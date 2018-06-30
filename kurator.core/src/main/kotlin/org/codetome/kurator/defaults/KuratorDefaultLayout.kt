package org.codetome.kurator.defaults

import kotlinx.html.*
import org.codetome.kurator.template.Layout
import org.codetome.kurator.template.template

object KuratorDefaultLayout : Layout(template { ctx ->
    val (page, site) = ctx
    html {
        body {
            h1 { text("Welcome to Kurator!") }
            p { text("This is the default layout. Change it by setting" +
                    " the `layout` property in your `Config`.") }
        }
    }
})
