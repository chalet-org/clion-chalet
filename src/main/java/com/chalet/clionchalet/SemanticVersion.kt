package com.chalet.clionchalet

class SemanticVersion(val major: Int = 0, val minor: Int = 0, val patch: Int = 0) {
    override fun toString(): String = "$major.$minor.$patch"
}