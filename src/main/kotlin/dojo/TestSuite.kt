package dojo

class TestSuite(private val testCases: List<() -> Unit>) {
    val numberOfTests: Int = testCases.count()
    fun run():String {
        val failures = collectFailures()
        var out = ""
        failures.forEachIndexed { i, failure ->
            out += "Failed test case ${i + 1}: \n"
            out += failure.e.assertionMessage
            val failureCause = failure.e.stackTrace[1]
            out += "${failureCause.fileName} @ ${failureCause.lineNumber}"
        }
        return out + "failures: ${failures.size}\n"
    }
    fun collectFailures(): List<dojo.Failure> {
        val failures = testCases.map {
            try {
                it.invoke()
            } catch (e: AssertionFailed) {
                return@map dojo.Failure(it, e)
            }
            return@map null
        }.filterNotNull()

        return failures
    }
}