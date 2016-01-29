/*
 * Copyright 2016 Taylor Caldwell
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.rithms.riot.api;

import java.util.Objects;
import java.util.logging.Level;

/**
 * Configuration class to use with the {@link RiotApi}.
 */
public class ApiConfig {

	private Level level = Level.WARNING;
	private String key = null;
	private String tournamentKey = null;
	private int requestTimeout = 0;
	private int asyncRequestTimeout = 10000;
	private int maxAsyncThreads = 0;

	@Override
	public ApiConfig clone() {
		return new ApiConfig().setAsyncRequestTimeout(getAsyncRequestTimeout()).setKey(getKey()).setRequestTimeout(getRequestTimeout())
				.setTournamentKey(getTournamentKey());
	}

	public int getAsyncRequestTimeout() {
		return asyncRequestTimeout;
	}

	public Level getDebugLevel() {
		return level;
	}

	public String getKey() {
		return key;
	}

	public int getMaxAsyncThreads() {
		return maxAsyncThreads;
	}

	public int getRequestTimeout() {
		return requestTimeout;
	}

	public String getTournamentKey() {
		return tournamentKey;
	}

	/**
	 * Sets how many milliseconds a call in {@link RiotApiAsync} waits for a response at most before timing out. This value can be set to
	 * zero to disable the request timeout. By default, the timeout value for asynchronous requests is 10 seconds.
	 * 
	 * <p>
	 * To set the timeout for synchronous requests use {@link #setRequestTimeout(int)} instead.
	 * </p>
	 *
	 * @param timeout
	 *            The maximum time for an asynchronous call to wait for a response until it times out
	 * @return This ApiConfig object for chaining
	 * @throws IllegalArgumentException
	 *             If the timeout value is smaller than {@code 0}
	 */
	public ApiConfig setAsyncRequestTimeout(int asyncRequestTimeout) {
		if (asyncRequestTimeout < 0) {
			throw new IllegalArgumentException("The timeout value must be greater than or equal to 0");
		}
		this.asyncRequestTimeout = asyncRequestTimeout;
		return this;
	}

	/**
	 * Sets the debug level for the Riot Api.
	 * 
	 * @param level
	 *            Debug level
	 * @return This ApiConfig object for chaining
	 */
	public ApiConfig setDebugLevel(Level level) {
		Objects.requireNonNull(level, "level must not be null");
		this.level = level;
		return this;
	}

	/**
	 * Sets the api key for the Riot Api. Most endpoints require this key to be set.
	 *
	 * @param key
	 *            Your api key
	 * @return This ApiConfig object for chaining
	 * @throws NullPointerException
	 *             If the {@code key} is null
	 */
	public ApiConfig setKey(String key) {
		Objects.requireNonNull(key, "key must not be null");
		this.key = key;
		return this;
	}

	/**
	 * Sets the maximum amount of threads for asynchronous api calls running at once. This value can be set to zero to disable the limit. By
	 * default, there is no limit.
	 * 
	 * <p>
	 * If you make asynchronous calls, and the current thread limit is reached, the api call will be queued and executed when resources
	 * become available.
	 * </p>
	 * 
	 * @param maxAsyncThreads
	 *            Max amount of threads to run at the same time
	 * @return This ApiConfig object for chaining
	 * @throws IllegalArgumentException
	 *             If the limit is smaller than {@code 0}
	 */
	public ApiConfig setMaxAsyncThreads(int maxAsyncThreads) {
		if (maxAsyncThreads < 0) {
			throw new IllegalArgumentException("The max amount of threads to run must be greater than or equal to 0");
		}
		this.maxAsyncThreads = maxAsyncThreads;
		return this;
	}

	/**
	 * Sets how many milliseconds a call in {@link RiotApi} should block at most until an api call is cancelled. This value can be set to
	 * zero to disable the request timeout. By default, there is no timeout.
	 * 
	 * <p>
	 * To set the timeout for asynchronous requests use {@link #setAsyncRequestTimeout(int)} instead.
	 * </p>
	 *
	 * @param timeout
	 *            The maximum time to wait for a response until a synchronous call fails
	 * @return This ApiConfig object for chaining
	 * @throws IllegalArgumentException
	 *             If the timeout value is smaller than {@code 0}
	 */
	public ApiConfig setRequestTimeout(int requestTimeout) {
		if (requestTimeout < 0) {
			throw new IllegalArgumentException("The timeout value must be greater than or equal to 0");
		}
		this.requestTimeout = requestTimeout;
		return this;
	}

	/**
	 * Sets the tournament api key for the Riot Api. Tournament-related endpoints require this key to be set.
	 *
	 * @param key
	 *            Your tournament api key
	 * @return This ApiConfig object for chaining
	 * @throws NullPointerException
	 *             If the {@code tournamentKey} is null
	 */
	public ApiConfig setTournamentKey(String tournamentKey) {
		Objects.requireNonNull(tournamentKey, "tournamentKey must not be null");
		this.tournamentKey = tournamentKey;
		return this;
	}
}