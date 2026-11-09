package app.aaps.core.data.model

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * In `commonTest`, so this runs through Kotlin/Native as well as the JVM. `kotlin.test` rather than
 * Truth and JUnit 5, because neither of those exists off the JVM.
 */
class SourceSensorExtensionsTest {

    @Test
    fun `all sensors support advanced filtering`() {
        SourceSensor.entries.forEach { sensor ->
            assertTrue(sensor.advancedFilteringSupported(), "Sensor $sensor should support advanced filtering")
        }
    }

    @Test
    fun `libre 1 and unknown need the flat check`() {
        assertTrue(SourceSensor.LIBRE_1.needsFlatBgCheck())
        assertTrue(SourceSensor.LIBRE_1.needsFlatBgCheck())
        assertTrue(SourceSensor.UNKNOWN.needsFlatBgCheck())
    }

    @Test
    fun `carelink follow needs the flat check because it comes over a cloud link`() {
        assertTrue(SourceSensor.MM_UNKNOWN.needsFlatBgCheck())
    }

    @Test
    fun `mq needs the flat check but the other juggluco ng sensors do not`() {
        // Juggluco sends the same name for a direct MQ sensor and for one relayed by the MQ cloud.
        assertTrue(SourceSensor.GLUTEC.needsFlatBgCheck())
        assertFalse(SourceSensor.SINOCARE.needsFlatBgCheck())
        assertFalse(SourceSensor.ANYTIME.needsFlatBgCheck())
    }

    @Test
    fun `both aidex generations need the flat check`() {
        assertTrue(SourceSensor.AIDEX.needsFlatBgCheck())
        assertTrue(SourceSensor.AIDEX_X.needsFlatBgCheck())
    }

    @Test
    fun `named native sources do not need the flat check`() {
        assertFalse(SourceSensor.DEXCOM_G6.needsFlatBgCheck())
        assertFalse(SourceSensor.DEXCOM_UNKNOWN.needsFlatBgCheck())
        assertFalse(SourceSensor.LIBRE_2.needsFlatBgCheck())
        assertFalse(SourceSensor.MEDTRUM_UNKNOWN.needsFlatBgCheck())
        assertFalse(SourceSensor.GLUPRO.needsFlatBgCheck())
        assertFalse(SourceSensor.SIBIONIC_GS1.needsFlatBgCheck())
        assertFalse(SourceSensor.SIBIONIC_GS3.needsFlatBgCheck())
        assertFalse(SourceSensor.ACCU_CHEK.needsFlatBgCheck())
        assertFalse(SourceSensor.CARESENS_AIR.needsFlatBgCheck())
    }
}
