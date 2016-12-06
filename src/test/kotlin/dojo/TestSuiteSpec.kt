package dojo

import io.kotlintest.specs.StringSpec

class TestSuiteSpec : StringSpec() {
    init {
        "should throw when false" {
            shouldThrow<AssertionFailed> {
                assertTrue(false)
            }
        }

        "should throw when true" {
            assertTrue(true)
        }

        "can create a suite from test cases" {
            val testsuite = TestSuite(listOf({
                assertTrue(1 == 1)
            }))
            testsuite.numberOfTests shouldBe 1
        }

        "can collectFailures and zero testCase when all tests pass" {
            val testsuite = TestSuite(listOf({
                assertTrue(1 == 1)
            }))
            testsuite.collectFailures().count() shouldBe 0
        }

        "collectFailures tells number of failed tests" {
            val testsuite = TestSuite(listOf({
                assertTrue(false)
            }, {
                assertTrue(false)
            }, {
                assertTrue(true)
            }))

            testsuite.collectFailures().count() shouldBe 2
        }

        "collectFailures tells failed test cases" {
            val failure1 = {
                assertTrue(false)
            }
            val failure2 = {
                assertTrue(false)
            }
            val testsuite = TestSuite(listOf(failure1, failure2, {
                assertTrue(true)
            }))

            testsuite.collectFailures()[0].testCase shouldBe failure1
            testsuite.collectFailures()[1].testCase shouldBe failure2
        }

        "run prints number of failures" {
            val failure1 = {
                assertTrue(false)
            }
            val failure2 = {
                assertTrue(false)
            }
            val testsuite = TestSuite(listOf(failure1, failure2, {
                assertTrue(true)
            }))

            testsuite.run().contains("failures: 2") shouldBe true
        }

        "run prints failed test cases" {
            val failure1 = {
                assertTrue(false)
            }
            val failure2 = {
                assertTrue(false)
            }
            val testsuite = TestSuite(listOf(failure1, failure2, {
                assertTrue(true)
            }))

            testsuite.run().contains("Failed test case 1:") shouldBe true
            testsuite.run().contains("Failed test case 2:") shouldBe true
        }

    }
}



