package includes

import kotlinx.html.div
import models.TestData
import org.codetome.kurator.template.Partial
import org.codetome.kurator.template.template

object Include : Partial<TestData>(template { (text) ->
    div {
        text("Text is: $text")
    }
})
