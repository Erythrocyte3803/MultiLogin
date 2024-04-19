package moe.caa.multilogin.core.resource.configuration.service.yggdrasil

import moe.caa.multilogin.core.auth.LoginProfile
import moe.caa.multilogin.core.resource.configuration.service.BaseService
import moe.caa.multilogin.core.resource.configuration.service.HttpMethodType
import moe.caa.multilogin.core.resource.configuration.service.ServiceType
import moe.caa.multilogin.core.resource.configuration.service.UUIDGenerateType
import java.net.URLEncoder

abstract class YggdrasilService(
    serviceId: Int,
    serviceName: String,
    uuidGenerateType: UUIDGenerateType,
    whitelist: Boolean,
    val trackIp: Boolean,
    val timeout: Int,
    val retry: Int,
    val delayRetry: Int,
) : BaseService(
    serviceId, serviceName, uuidGenerateType, whitelist
) {

    abstract val httpMethodType: HttpMethodType;
    abstract fun generateAuthUrl(loginProfile: LoginProfile): String
    abstract fun generatePostContent(loginProfile: LoginProfile): String

    fun generateIpContent(byteArray: ByteArray, ip: String?) = generateIpContent(String(byteArray), ip)

    fun generateIpContent(string: String, ip: String?) = if (ip != null) string
        .replace("{0}", ip)
        .replace("{ip}", ip) else ""
}

class YggdrasilCustomService(
    serviceId: Int,
    serviceName: String,
    uuidGenerateType: UUIDGenerateType,
    whitelist: Boolean,
    trackIp: Boolean,
    timeout: Int,
    retry: Int,
    delayRetry: Int,
    override val httpMethodType: HttpMethodType,
    private val authUrl: String,
    private val ipContent: String,
    private val postContent: String
) : YggdrasilService(
    serviceId,
    serviceName,
    uuidGenerateType,
    whitelist,
    trackIp,
    timeout,
    retry,
    delayRetry
) {
    override val serviceType = ServiceType.CUSTOM_YGGDRASIL

    override fun generateAuthUrl(loginProfile: LoginProfile): String {
        val encodedUsername = URLEncoder.encode(loginProfile.username, Charsets.UTF_8)
        val encodedServerId = URLEncoder.encode(loginProfile.serverId, Charsets.UTF_8)
        val ipContent = generateIpContent(ipContent, loginProfile.playerIp)

        return authUrl
            .replace("{0}", encodedUsername)
            .replace("{1}", encodedServerId)
            .replace("{2}", ipContent)
            .replace("{username}", encodedUsername)
            .replace("{server_id)", encodedServerId)
            .replace("{ip_content}", ipContent)
    }

    override fun generatePostContent(loginProfile: LoginProfile): String {
        if (httpMethodType == HttpMethodType.GET) throw UnsupportedOperationException("http get")
        val encodedUsername = URLEncoder.encode(loginProfile.username, Charsets.UTF_8)
        val encodedServerId = URLEncoder.encode(loginProfile.serverId, Charsets.UTF_8)
        val ipContent = generateIpContent(ipContent, loginProfile.playerIp)

        return postContent
            .replace("{0}", encodedUsername)
            .replace("{1}", encodedServerId)
            .replace("{2}", ipContent)
            .replace("{username}", encodedUsername)
            .replace("{server_id)", encodedServerId)
            .replace("{ip_content}", ipContent)
    }

}

class YggdrasilBlessingSkinService(
    serviceId: Int,
    serviceName: String,
    uuidGenerateType: UUIDGenerateType,
    whitelist: Boolean,
    trackIp: Boolean,
    timeout: Int,
    retry: Int,
    delayRetry: Int,
    private val apiRoot: String
) : YggdrasilService(
    serviceId,
    serviceName,
    uuidGenerateType,
    whitelist,
    trackIp,
    timeout,
    retry,
    delayRetry
) {
    override val httpMethodType = HttpMethodType.GET
    override val serviceType = ServiceType.BLESSING_SKIN
    companion object {
        // sessionserver/session/minecraft/hasJoined?username={0}&serverId={1}{2}
        private val APPEND_YGGDRASIL_URL_BYTES: ByteArray = byteArrayOf(
            115,
            101,
            115,
            115,
            105,
            111,
            110,
            115,
            101,
            114,
            118,
            101,
            114,
            47,
            115,
            101,
            115,
            115,
            105,
            111,
            110,
            47,
            109,
            105,
            110,
            101,
            99,
            114,
            97,
            102,
            116,
            47,
            104,
            97,
            115,
            74,
            111,
            105,
            110,
            101,
            100,
            63,
            117,
            115,
            101,
            114,
            110,
            97,
            109,
            101,
            61,
            123,
            48,
            125,
            38,
            115,
            101,
            114,
            118,
            101,
            114,
            73,
            100,
            61,
            123,
            49,
            125,
            123,
            50,
            125
        )
        private val BLESSING_SKIN_YGGDRASIL_URL_IP_CONTENT_BYTES: ByteArray =
            YggdrasilOfficialService.VANILLA_YGGDRASIL_URL_IP_CONTENT_BYTES
    }

    override fun generateAuthUrl(loginProfile: LoginProfile): String {
        val encodedUsername = URLEncoder.encode(loginProfile.username, Charsets.UTF_8)
        val encodedServerId = URLEncoder.encode(loginProfile.serverId, Charsets.UTF_8)
        val ipContent = generateIpContent(BLESSING_SKIN_YGGDRASIL_URL_IP_CONTENT_BYTES, loginProfile.playerIp)

        return "$apiRoot/" + String(APPEND_YGGDRASIL_URL_BYTES)
            .replace("{0}", encodedUsername)
            .replace("{1}", encodedServerId)
            .replace("{2}", ipContent)
            .replace("{username}", encodedUsername)
            .replace("{server_id)", encodedServerId)
            .replace("{ip_content}", ipContent)
    }

    override fun generatePostContent(loginProfile: LoginProfile): String {
        throw UnsupportedOperationException("http get")
    }

}

class YggdrasilOfficialService(
    serviceId: Int,
    serviceName: String,
    uuidGenerateType: UUIDGenerateType,
    whitelist: Boolean,
    trackIp: Boolean,
    timeout: Int,
    retry: Int,
    delayRetry: Int
) : YggdrasilService(
    serviceId,
    serviceName,
    uuidGenerateType,
    whitelist,
    trackIp,
    timeout,
    retry,
    delayRetry
) {
    override val httpMethodType = HttpMethodType.GET
    override val serviceType = ServiceType.OFFICIAL
    companion object {
        // https://sessionserver.mojang.com/session/minecraft/hasJoined?username={0}&serverId={1}{2}
        private val VANILLA_YGGDRASIL_URL_BYTES: ByteArray = byteArrayOf(
            104,
            116,
            116,
            112,
            115,
            58,
            47,
            47,
            115,
            101,
            115,
            115,
            105,
            111,
            110,
            115,
            101,
            114,
            118,
            101,
            114,
            46,
            109,
            111,
            106,
            97,
            110,
            103,
            46,
            99,
            111,
            109,
            47,
            115,
            101,
            115,
            115,
            105,
            111,
            110,
            47,
            109,
            105,
            110,
            101,
            99,
            114,
            97,
            102,
            116,
            47,
            104,
            97,
            115,
            74,
            111,
            105,
            110,
            101,
            100,
            63,
            117,
            115,
            101,
            114,
            110,
            97,
            109,
            101,
            61,
            123,
            48,
            125,
            38,
            115,
            101,
            114,
            118,
            101,
            114,
            73,
            100,
            61,
            123,
            49,
            125,
            123,
            50,
            125
        )

        // &ip={0}
        val VANILLA_YGGDRASIL_URL_IP_CONTENT_BYTES: ByteArray = byteArrayOf(38, 105, 112, 61, 123, 48, 125)
    }

    override fun generateAuthUrl(loginProfile: LoginProfile): String {
        val encodedUsername = URLEncoder.encode(loginProfile.username, Charsets.UTF_8)
        val encodedServerId = URLEncoder.encode(loginProfile.serverId, Charsets.UTF_8)
        val ipContent = generateIpContent(VANILLA_YGGDRASIL_URL_IP_CONTENT_BYTES, loginProfile.playerIp)

        return String(VANILLA_YGGDRASIL_URL_BYTES)
            .replace("{0}", encodedUsername)
            .replace("{1}", encodedServerId)
            .replace("{2}", ipContent)
            .replace("{username}", encodedUsername)
            .replace("{server_id)", encodedServerId)
            .replace("{ip_content}", ipContent)
    }

    override fun generatePostContent(loginProfile: LoginProfile) =
        throw UnsupportedOperationException("http post")
}