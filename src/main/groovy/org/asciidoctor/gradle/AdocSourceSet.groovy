package org.asciidoctor.gradle

import org.gradle.api.Named
import org.gradle.model.Managed

/**
 * Created by pledbrook on 25/11/2015.
 */
@Managed
interface AdocSourceSet extends Named {
    String getSrcDir()
    void setSrcDir(String dir)

    Set<String> getIncludes()
    void setIncludes(Set<String> includes)
}
