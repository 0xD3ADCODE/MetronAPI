package com.github.atsumerudev.api.metron.network

import com.github.atsumerudev.api.metron.model.Issue
import com.github.atsumerudev.api.metron.model.ResponseResult
import com.github.atsumerudev.api.metron.model.Serie
import com.github.atsumerudev.api.metron.model.info.*
import com.github.atsumerudev.api.metron.model.short.ShortIssue
import com.github.atsumerudev.api.metron.model.short.ShortSerie
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MetronService {

    // Arc
    @GET("/api/arc/")
    fun getArcs(
        @Query("page") page: Int,
        @Query("name") name: String?,
        @Query("modified_gt") modifiedGt: String?
    ): Single<ResponseResult<Arc>>

    @GET("/api/arc/{id}/")
    fun getArc(@Path("id") arkId: Int): Single<Arc>

    @GET("/api/arc/{id}/issue_list/")
    fun getArcIssueList(@Path("id") arkId: Int): Single<ResponseResult<ShortIssue>>

    // Character
    @GET("/api/character/")
    fun getCharacters(
        @Query("page") page: Int,
        @Query("name") name: String?,
        @Query("modified_gt") modifiedGt: String?
    ): Single<ResponseResult<Character>>

    @GET("/api/character/{id}/")
    fun getCharacter(@Path("id") characterId: Int): Single<Character>

    @GET("/api/character/{id}/issue_list/")
    fun getCharacterIssueList(@Path("id") characterId: Int): Single<ResponseResult<ShortIssue>>

    // Creator
    @GET("/api/creator/")
    fun getCreators(
        @Query("page") page: Int,
        @Query("name") name: String?,
        @Query("modified_gt") modifiedGt: String?
    ): Single<ResponseResult<Creator>>

    @GET("/api/creator/{id}/")
    fun getCreator(@Path("id") creatorId: Int): Single<Creator>

    // Issue
    @GET("/api/issue/")
    fun getIssues(
        @Query("page") page: Int,
        @Query("number") number: Int?,
        @Query("cover_month") coverMonth: Int?,
        @Query("cover_year") coverYear: Int?,
        @Query("publisher_id") publisherId: Int?,
        @Query("publisher_name") publisherName: String?,
        @Query("series_id") seriesId: Int?,
        @Query("series_name") seriesName: String?,
        @Query("series_volume") seriesVolume: Int?,
        @Query("series_year_began") seriesYearBegan: Int?,
        @Query("sku") sku: String?,
        @Query("store_date") storeDate: String?,
        @Query("store_date_range_after") storeDateRangeAfter: String?,
        @Query("store_date_range_before") storeDateRangeBefore: String?,
        @Query("upc") upc: String?,
        @Query("modified_gt") modifiedGt: String?
    ): Single<ResponseResult<ShortIssue>>

    @GET("/api/issue/{id}/")
    fun getIssue(@Path("id") issueId: Int): Single<Issue>

    // Publisher
    @GET("/api/publisher/")
    fun getPublishers(
        @Query("page") page: Int,
        @Query("name") name: String?,
        @Query("modified_gt") modifiedGt: String?
    ): Single<ResponseResult<Publisher>>

    @GET("/api/publisher/{id}/")
    fun getPublisher(@Path("id") publisherId: Int): Single<Publisher>

    @GET("/api/publisher/{id}/series_list/")
    fun getPublisherSeriesList(@Path("id") publisherId: Int): Single<ResponseResult<ShortSerie>>

    // Role
    @GET("/api/role/")
    fun getRoles(
        @Query("page") page: Int,
        @Query("name") name: String?,
        @Query("modified_gt") modifiedGt: String?
    ): Single<ResponseResult<Role>>

    // Serie
    @GET("/api/series/")
    fun getSeries(
        @Query("page") page: Int,
        @Query("name") name: String?,
        @Query("publisher_id") publisherId: Int?,
        @Query("publisher_name") publisherName: String?,
        @Query("series_type") seriesType: String?,
        @Query("series_type_id") seriesTypeId: Int?,
        @Query("volume") volume: Int?,
        @Query("year_began") yearBegan: Int?,
        @Query("year_end") yearEnd: Int?,
        @Query("modified_gt") modifiedGt: String?
    ): Single<ResponseResult<ShortSerie>>

    @GET("/api/series/{id}/")
    fun getSerie(@Path("id") serieId: Int): Single<Serie>

    @GET("/api/series/{id}/issue_list/")
    fun getSerieIssueList(@Path("id") serieId: Int): Single<ResponseResult<ShortIssue>>

    // Team
    @GET("/api/team/")
    fun getTeams(
        @Query("page") page: Int,
        @Query("name") name: String?,
        @Query("modified_gt") modifiedGt: String?
    ): Single<ResponseResult<Team>>

    @GET("/api/team/{id}/")
    fun getTeam(@Path("id") teamId: Int): Single<Team>

    @GET("/api/team/{id}/issue_list/")
    fun getTeamIssueList(@Path("id") teamId: Int): Single<ResponseResult<ShortIssue>>
}