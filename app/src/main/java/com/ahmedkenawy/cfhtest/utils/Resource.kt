package com.ahmedkenawy.cfhtest.utils

/**
 * A sealed class representing the state of a resource. It is used to encapsulate the different
 * possible outcomes of an operation, such as a network request or a data fetch. This class helps
 * manage the state and provide meaningful feedback to the user or the UI.
 *
 * @param T The type of data contained in the resource.
 */
sealed class Resource<T> {

    /**
     * Represents a successful response.
     *
     * @property data The data returned by the successful operation. It can be null.
     */
    data class Success<T>(val data: T?) : Resource<T>()

    /**
     * Represents an error response.
     *
     * @property message A message describing the error.
     * @property data Optionally contains the data returned when an error occurred.
     */
    data class Error<T>(val message: String, val data: T? = null) : Resource<T>()

    /**
     * Represents a loading state.
     * This indicates that the operation is ongoing and no data is available yet.
     */
    class Loading<T> : Resource<T>()
}
