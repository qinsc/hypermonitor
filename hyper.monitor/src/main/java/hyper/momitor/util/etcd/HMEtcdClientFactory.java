/*
 * Copyright 2013 Diwaker Gupta
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hyper.momitor.util.etcd;

import com.google.common.base.Optional;

import retrofit.client.Client;

/** Factory for Etcd clients. */
public final class HMEtcdClientFactory {
  private HMEtcdClientFactory() {
    // Factory
  }

  public static HMEtcdClient newInstance() {
    return newInstance("http://127.0.0.1:2379");
  }

  public static HMEtcdClient newInstance(final String server) {
    return new HMEtcdClientImpl(Optional.<Client>absent(), server);
  }

  public static HMEtcdClient newInstance(final Client client,
      final String server) {
    return new HMEtcdClientImpl(Optional.of(client), server);
  }
}
