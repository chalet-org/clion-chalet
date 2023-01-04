package com.chalet.clionchalet

import com.intellij.openapi.vfs.VirtualFile
import com.intellij.testFramework.LightVirtualFile
import com.jetbrains.jsonSchema.extension.JsonSchemaFileProvider
import com.jetbrains.jsonSchema.extension.SchemaType
import com.jetbrains.jsonSchema.impl.JsonSchemaVersion
import java.nio.charset.Charset

class ChaletBuildSchemaProvider : JsonSchemaFileProvider {
    private var cachedFile: LightVirtualFile? = null

    override fun isAvailable(file: VirtualFile): Boolean {
        return SchemaUtils.isBuildFile(file)
    }

    override fun getName(): String {
        return "chalet.json"
    }

    override fun getSchemaFile(): VirtualFile {
        return createVirtualSchema()
    }

    override fun getSchemaType(): SchemaType {
        return SchemaType.embeddedSchema
    }

    override fun getSchemaVersion(): JsonSchemaVersion {
        return JsonSchemaVersion.SCHEMA_7
    }

    override fun getThirdPartyApiInformation(): String? {
        return null
    }

    override fun isUserVisible(): Boolean {
        return true
    }

    override fun getRemoteSource(): String? {
        return null
    }

    private fun createVirtualSchema(): LightVirtualFile {
        return if (cachedFile != null) {
            cachedFile as LightVirtualFile
        } else {
            cachedFile = LightVirtualFile(ChaletBuildSchemaProviderFactory.SCHEMA_FILE_NAME)

            val chalet = ChaletVersion.Release
            val str = getProcessOutput(chalet, arrayOf("query", "schema-chalet-json")) ?: throw Error("Chalet not found")

            cachedFile!!.setBinaryContent(str.toByteArray(Charset.defaultCharset()))

            println("created chalet schema")

            cachedFile as LightVirtualFile
        }

    }
}