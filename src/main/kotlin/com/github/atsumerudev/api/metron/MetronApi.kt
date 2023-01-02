package com.github.atsumerudev.api.metron

import com.github.atsumerudev.api.metron.model.Issue
import com.github.atsumerudev.api.metron.model.ResponseResult
import com.github.atsumerudev.api.metron.model.Serie
import com.github.atsumerudev.api.metron.model.info.*
import com.github.atsumerudev.api.metron.model.short.ShortIssue
import com.github.atsumerudev.api.metron.model.short.ShortSerie
import com.github.atsumerudev.api.metron.network.MetronService
import com.google.gson.GsonBuilder
import io.reactivex.Single
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MetronApi {
    private lateinit var metronService: MetronService
    private lateinit var authorizationCredentials: String

    private val gson = GsonBuilder().create()
    private val loggingInterceptor = HttpLoggingInterceptor()

    /**
     * Init method for [MetronApi]
     *
     * @param builder customized [OkHttpClient.Builder] instance
     * @param userName [String] account username value from [Metron](https://metron.cloud/) site
     * @param password [String] account password value from [Metron](https://metron.cloud/) site
     * @param apiEndpoint Metron API endpoint [String] link. Default is: https://metron.cloud/api/
     * @param userAgent Metron API app/library [String] User-Agent. Default is: MetronAPI Library
     * @param httpConnectTimeoutMs [OkHttpClient] connection timeout in [Long] millis. Default is: 15000ms
     * @param httpReadTimeoutMs [OkHttpClient] read timeout in [Long] millis. Default is: 25000ms
     * @param isDebug sets logging mode. When debug, [HttpLoggingInterceptor.level] will be configured to [HttpLoggingInterceptor.Level.BODY]
     * @return RXJava [Single] object that holds [ResponseResult] with [List] of [Arc]
     */
    @JvmStatic
    fun init(
        builder: OkHttpClient.Builder = OkHttpClient.Builder(),
        userName: String? = null,
        password: String? = null,
        apiEndpoint: String = "https://metron.cloud/api/",
        userAgent: String = "MetronAPI Library",
        httpConnectTimeoutMs: Long = 15000,
        httpReadTimeoutMs: Long = 25000,
        isDebug: Boolean = false
    ) {
        if (userName.isNullOrEmpty() || password.isNullOrEmpty()) {
            throw IllegalArgumentException(
                "Provide your Metron Username and Password to use this library. It can't be null or empty"
            )
        }

        authorizationCredentials = Credentials.basic(userName, password)

        val httpClient = builder.connectTimeout(httpConnectTimeoutMs, TimeUnit.MILLISECONDS)
            .readTimeout(httpReadTimeoutMs, TimeUnit.MILLISECONDS)
            .addInterceptor(loggingInterceptor.apply {
                level = if (isDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .addInterceptor { chain ->
                var request = chain.request()
                request = request.newBuilder()
                    .removeHeader("User-Agent")
                    .removeHeader("Authorization")
                    .addHeader("User-Agent", userAgent)
                    .addHeader("Authorization", authorizationCredentials)
                    .build()

                chain.proceed(request)
            }
            .build()

        val restAdapter = Retrofit.Builder()
            .baseUrl(apiEndpoint)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        metronService = restAdapter.create(MetronService::class.java)
    }

    // Ark
    /**
     * Get [ResponseResult] with [List] of [Arc] objects
     *
     * @param page page [Int] index starting from 1
     * @param name nullable [String] parameter for searching [Arc] with that name. Unused when null
     * @param modifiedGt nullable [String] parameter for filtering [Arc] with modified date greater than provided. Unused when null
     * @return RXJava [Single] object that holds [ResponseResult] with [List] of [Arc]
     */
    @JvmStatic fun getArcs(page: Int, name: String?, modifiedGt: String?): Single<ResponseResult<Arc>> {
        return metronService.getArcs(page, name, modifiedGt)
    }

    /**
     * Get [Arc] object by id
     *
     * @param arkId [Int] id of [Arc]
     * @return RXJava [Single] object that holds [Arc]
     */
    @JvmStatic fun getArc(arkId: Int): Single<Arc> {
        return metronService.getArc(arkId)
    }

    /**
     * Get [ResponseResult] with [ShortIssue] object by Arc id
     *
     * @param arkId [Int] id of [Arc]
     * @return RXJava [Single] object that holds [ResponseResult] with [ShortIssue]
     */
    @JvmStatic fun getArcIssueList(arkId: Int): Single<ResponseResult<ShortIssue>> {
        return metronService.getArcIssueList(arkId)
    }

    // Character
    /**
     * Get [ResponseResult] with [List] of [Character] objects
     *
     * @param page page [Int] index starting from 1
     * @param name nullable [String] parameter for searching [Character] with that name. Unused when null
     * @param modifiedGt nullable [String] parameter for filtering [Character] with modified date greater than provided. Unused when null
     * @return RXJava [Single] object that holds [ResponseResult] with [List] of [Character]
     */
    @JvmStatic fun getCharacters(page: Int, name: String?, modifiedGt: String?): Single<ResponseResult<Character>> {
        return metronService.getCharacters(page, name, modifiedGt)
    }

    /**
     * Get [Character] object by id
     *
     * @param characterId [Int] id of [Character]
     * @return RXJava [Single] object that holds [Character]
     */
    @JvmStatic fun getCharacter(characterId: Int): Single<Character> {
        return metronService.getCharacter(characterId)
    }

    /**
     * Get [ResponseResult] with [ShortIssue] object by Character id
     *
     * @param characterId [Int] id of [Character]
     * @return RXJava [Single] object that holds [ResponseResult] with [ShortIssue]
     */
    @JvmStatic fun getCharacterIssueList(characterId: Int): Single<ResponseResult<ShortIssue>> {
        return metronService.getCharacterIssueList(characterId)
    }

    // Creator
    /**
     * Get [ResponseResult] with [List] of [Creator] objects
     *
     * @param page page [Int] index starting from 1
     * @param name nullable [String] parameter for searching [Creator] with that name. Unused when null
     * @param modifiedGt nullable [String] parameter for filtering [Creator] with modified date greater than provided. Unused when null
     * @return RXJava [Single] object that holds [ResponseResult] with [List] of [Creator]
     */
    @JvmStatic fun getCreators(page: Int, name: String?, modifiedGt: String?): Single<ResponseResult<Creator>> {
        return metronService.getCreators(page, name, modifiedGt)
    }

    /**
     * Get [Creator] object by id
     *
     * @param creatorId [Int] id of [Creator]
     * @return RXJava [Single] object that holds [Creator]
     */
    @JvmStatic fun getCreator(creatorId: Int): Single<Creator> {
        return metronService.getCreator(creatorId)
    }

    // Issue
    /**
     * Get [ResponseResult] with [List] of [ShortIssue] objects
     *
     * @param page page [Int] index starting from 1
     * @param number nullable [Int] parameter for searching [ShortIssue] with Issue number. Unused when null
     * @param coverMonth nullable [Int] parameter for searching [ShortIssue] with Cover month value. Unused when null
     * @param coverYear nullable [Int] parameter for searching [ShortIssue] with Cover year value. Unused when null
     * @param publisherId nullable [Int] parameter for searching [ShortIssue] with [Publisher] id. Unused when null
     * @param publisherName nullable [String] parameter for searching [ShortIssue] with [Publisher] name. Unused when null
     * @param seriesId nullable [Int] parameter for searching [ShortIssue] with [Serie] id. Unused when null
     * @param seriesName nullable [String] parameter for searching [ShortIssue] with [Serie] name. Unused when null
     * @param seriesVolume nullable [String] parameter for searching [ShortIssue] with [Serie] Volume number. Unused when null
     * @param seriesYearBegan nullable [String] parameter for searching [ShortIssue] with [Serie] begin publishing Year. Unused when null
     * @param sku nullable [String] parameter for searching [ShortIssue] with [Stock Keeping Unit](https://www.wikiwand.com/en/Stock_keeping_unit). Unused when null
     * @param storeDate nullable [String] parameter for searching [ShortIssue] with Store Date. Unused when null
     * @param storeDateRangeAfter nullable [String] parameter for searching [ShortIssue] with Store Date Range After. Unused when null
     * @param storeDateRangeBefore nullable [String] parameter for searching [ShortIssue] with Store Date Range Before. Unused when null
     * @param upc nullable [String] parameter for searching [ShortIssue] with [Universal Product Code](https://www.wikiwand.com/ru/Universal_Product_Code). Unused when null
     * @return RXJava [Single] object that holds [ResponseResult] with [List] of [ShortIssue]
     */
    @JvmStatic fun getIssues(page: Int,
                             number: Int?,
                             coverMonth: Int?,
                             coverYear: Int?,
                             publisherId: Int?,
                             publisherName: String?,
                             seriesId: Int?,
                             seriesName: String?,
                             seriesVolume: Int?,
                             seriesYearBegan: Int?,
                             sku: String?,
                             storeDate: String?,
                             storeDateRangeAfter: String?,
                             storeDateRangeBefore: String?,
                             upc: String?,
                             modifiedGt: String?): Single<ResponseResult<ShortIssue>> {
        return metronService.getIssues(page, number, coverMonth, coverYear, publisherId, publisherName, seriesId, seriesName,
            seriesVolume, seriesYearBegan, sku, storeDate, storeDateRangeAfter, storeDateRangeBefore, upc, modifiedGt)
    }

    /**
     * Get [Issue] object by id
     *
     * @param issueId [Int] id of [Issue]
     * @return RXJava [Single] object that holds [Issue]
     */
    @JvmStatic fun getIssue(issueId: Int): Single<Issue> {
        return metronService.getIssue(issueId)
    }

    // Publisher
    /**
     * Get [ResponseResult] with [List] of [Publisher] objects
     *
     * @param page page [Int] index starting from 1
     * @param name nullable [String] parameter for searching [Publisher] with that name. Unused when null
     * @param modifiedGt nullable [String] parameter for filtering [Publisher] with modified date greater than provided. Unused when null
     * @return RXJava [Single] object that holds [ResponseResult] with [List] of [Publisher]
     */
    @JvmStatic fun getPublishers(page: Int, name: String?, modifiedGt: String?): Single<ResponseResult<Publisher>> {
        return metronService.getPublishers(page, name, modifiedGt)
    }

    /**
     * Get [Publisher] object by id
     *
     * @param publisherId [Int] id of [Publisher]
     * @return RXJava [Single] object that holds [Publisher]
     */
    @JvmStatic fun getPublisher(publisherId: Int): Single<Publisher> {
        return metronService.getPublisher(publisherId)
    }

    /**
     * Get [ResponseResult] with [ShortSerie] object by Publisher id
     *
     * @param publisherId [Int] id of [Publisher]
     * @return RXJava [Single] object that holds [ResponseResult] with [ShortSerie]
     */
    @JvmStatic fun getPublisherSeriesList(publisherId: Int): Single<ResponseResult<ShortSerie>> {
        return metronService.getPublisherSeriesList(publisherId)
    }

    // Role
    /**
     * Get [ResponseResult] with [List] of [Role] objects
     *
     * @param page page [Int] index starting from 1
     * @param name nullable [String] parameter for searching [Role] with that name. Unused when null
     * @param modifiedGt nullable [String] parameter for filtering [Role] with modified date greater than provided. Unused when null
     * @return RXJava [Single] object that holds [ResponseResult] with [List] of [Role]
     */
    @JvmStatic fun getRoles(page: Int, name: String?, modifiedGt: String?): Single<ResponseResult<Role>> {
        return metronService.getRoles(page, name, modifiedGt)
    }

    // Serie
    /**
     * Get [ResponseResult] with [List] of [ShortSerie] objects
     *
     * @param page page [Int] index starting from 1
     * @param name nullable [String] parameter for searching [ShortSerie] with that name. Unused when null
     * @param publisherId nullable [Int] parameter for searching [ShortSerie] with [Publisher] id. Unused when null
     * @param publisherName nullable [String] parameter for searching [ShortSerie] with [Publisher] name. Unused when null
     * @param seriesType nullable [String] parameter for searching [ShortSerie] with Serie Type. Unused when null
     * @param seriesTypeId nullable [Int] parameter for searching [ShortSerie] with [Serie] Id. Unused when null
     * @param volume nullable [Int] parameter for searching [ShortSerie] with Volume number. Unused when null
     * @param yearBegan nullable [Int] parameter for searching [ShortSerie] with begin publishing Year. Unused when null
     * @param yearEnd nullable [Int] parameter for searching [ShortSerie] with end publishing Year. Unused when null
     * @param modifiedGt nullable [String] parameter for filtering [ShortSerie] with modified date greater than provided. Unused when null
     * @return RXJava [Single] object that holds [ResponseResult] with [List] of [ShortSerie]
     */
    @JvmStatic fun getSeries(page: Int,
                             name: String?,
                             publisherId: Int?,
                             publisherName: String?,
                             seriesType: String?,
                             seriesTypeId: Int?,
                             volume: Int?,
                             yearBegan: Int?,
                             yearEnd: Int?,
                             modifiedGt: String?): Single<ResponseResult<ShortSerie>> {
        return metronService.getSeries(page, name, publisherId, publisherName, seriesType, seriesTypeId, volume, yearBegan, yearEnd, modifiedGt)
    }

    /**
     * Get [Serie] object by id
     *
     * @param serieId [Int] id of [Serie]
     * @return RXJava [Single] object that holds [Serie]
     */
    @JvmStatic fun getSerie(serieId: Int): Single<Serie> {
        return metronService.getSerie(serieId)
    }

    /**
     * Get [ResponseResult] with [ShortIssue] object by Publisher id
     *
     * @param serieId [Int] id of [Serie]
     * @return RXJava [Single] object that holds [ResponseResult] with [ShortIssue]
     */
    @JvmStatic fun getSerieIssueList(serieId: Int): Single<ResponseResult<ShortIssue>> {
        return metronService.getSerieIssueList(serieId)
    }

    // Team
    /**
     * Get [ResponseResult] with [List] of [Team] objects
     *
     * @param page page [Int] index starting from 1
     * @param name nullable [String] parameter for searching [Team] with that name. Unused when null
     * @param modifiedGt nullable [String] parameter for filtering [Team] with modified date greater than provided. Unused when null
     * @return RXJava [Single] object that holds [ResponseResult] with [List] of [Team]
     */
    @JvmStatic fun getTeams(page: Int, name: String?, modifiedGt: String?): Single<ResponseResult<Team>> {
        return metronService.getTeams(page, name, modifiedGt)
    }

    /**
     * Get [Team] object by id
     *
     * @param teamId [Int] id of [Team]
     * @return RXJava [Single] object that holds [Team]
     */
    @JvmStatic fun getTeam(teamId: Int): Single<Team> {
        return metronService.getTeam(teamId)
    }

    /**
     * Get [ResponseResult] with [ShortIssue] object by Publisher id
     *
     * @param teamId [Int] id of [Team]
     * @return RXJava [Single] object that holds [ResponseResult] with [ShortIssue]
     */
    @JvmStatic fun getTeamIssueList(teamId: Int): Single<ResponseResult<ShortIssue>> {
        return metronService.getTeamIssueList(teamId)
    }
}