package org.codetome.kurator

import org.codetome.kurator.data.user.configure
import org.codetome.kurator.service.DocumentLoader
import org.codetome.kurator.service.SiteGenerator
import org.junit.jupiter.api.*
import java.io.File
import java.util.*

@DisplayName("Given a site generator")
class StressTest {

    val sourceDir = File("src/test/resources/${UUID.randomUUID()}")
    val destinatinDir = File("src/test/resources/${UUID.randomUUID()}")
    val fileCount = 1000


    @BeforeEach
    fun setUp() {
        sourceDir.mkdirs()
    }

    @AfterEach
    fun tearDown() {
        sourceDir.deleteRecursively()
        destinatinDir.deleteRecursively()
    }


    @DisplayName("it should be able to generate 10.000 pages within the time limit")
    @Test
    @Disabled
    fun testGenerationSpeed() {
        val template = File("src/test/resources/templates/test_template.md")
        (0 until fileCount).forEach {
            template.copyTo(File("src/test/resources/${sourceDir.name}/collections/blog/${UUID.randomUUID()}.md"))
        }

        SiteGenerator(DocumentLoader()).generate(configure {
            sourceDir = this@StressTest.sourceDir.path
            destinationDir
            collections {
                collection<Unit> {
                    name = "blog"
                    defaultValues = Unit
                }
            }
        })
    }

}
