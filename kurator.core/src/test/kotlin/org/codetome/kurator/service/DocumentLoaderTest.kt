package org.codetome.kurator.service

import org.codetome.kurator.data.domain.CollectionConfig
import org.codetome.kurator.data.user.configure
import org.codetome.kurator.defaults.KuratorDefaultLayout
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DocumentLoaderTest {

    lateinit var target: DocumentLoader

    @BeforeEach
    fun setUp() {
        target = DocumentLoader()
    }

    @Test
    fun test() {
        val result = target.load("src/test/resources/templates/test_template.md", config, CollectionConfig(
                name = "foo",
                permalink = "bar",
                layout = KuratorDefaultLayout,
                defaultValues = Unit
        ))

        println("Page data: '$result'.")
    }

    companion object {

        private val config = configure { }
    }
}
