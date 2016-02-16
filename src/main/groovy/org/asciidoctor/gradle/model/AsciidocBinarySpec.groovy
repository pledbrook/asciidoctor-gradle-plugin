package org.asciidoctor.gradle.model

import org.gradle.model.Managed
import org.gradle.platform.base.BinarySpec

/**
 * Created by pledbrook on 03/02/2016.
 */
@Managed
interface AsciidocBinarySpec extends BinarySpec {
    String getOutputDir()
    void setOutputDir(String dir)
}
