package net.dean.jraw.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import net.dean.jraw.RedditClient
import net.dean.jraw.databind.UnixTimeDeserializer
import net.dean.jraw.references.Referenceable
import net.dean.jraw.references.UserReference
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class Account(
    /** The amount of Karma this user has acquired through comments */
    val commentKarma: Int,

    @JsonProperty("created_utc")
    @JsonDeserialize(using = UnixTimeDeserializer::class)
    override val created: Date,

    /** A unique identifier generated by reddit */
    val id: String,

    /** If the currently logged in user is friends with this account */
    val isFriend: Boolean,

    /** If this user is a moderator */
    @JsonProperty("is_mod") val isModerator: Boolean,

    /** If this property is true, the user has reddit Gold */
    @JsonProperty("is_gold") val hasGold: Boolean,
    val hasSubscribed: Boolean,

    /** True if this user has verified ownership of the email address used to create their account */
    val hasVerifiedEmail: Boolean,

    /** The amount of karma gained from submitting links */
    val linkKarma: Int,

    /** The name chosen for this account by a real person */
    val name: String
) : RedditObject(KindConstants.ACCOUNT), Created, Referenceable<UserReference> {
    override fun toReference(reddit: RedditClient) = reddit.user(name)
}

