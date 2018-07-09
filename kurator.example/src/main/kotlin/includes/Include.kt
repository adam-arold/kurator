package includes

import kotlinx.html.div
import models.TestData
import org.codetome.kurator.template.Partial
import org.codetome.kurator.template.buildTemplate

object Include : Partial<TestData>(buildTemplate { (text) ->
    div {
        text("Text is: $text")
    }
})
