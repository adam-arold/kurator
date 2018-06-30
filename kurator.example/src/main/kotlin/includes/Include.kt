package includes

import kotlinx.html.div
import models.TestData
import org.codetome.kurator.template.Partial
import org.codetome.kurator.template.partial

object Include : Partial<TestData>(partial { (text) ->
    div {
        text("Text is: $text")
    }
})
