/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package store.zabbix.gateway.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import reactor.core.publisher.Mono;
import store.zabbix.gateway.handler.HystrixFallbackHandler;
import store.zabbix.gateway.handler.ImageCodeHandler;

/**
 * @author lengleng
 * @date 2019/2/1
 * 路由配置信息
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class RouterFunctionConfiguration {

	private final HystrixFallbackHandler hystrixFallbackHandler;
	private final ImageCodeHandler imageCodeHandler;

	@Bean
	public RouterFunction routerFunction() {
		return RouterFunctions.route(
			RequestPredicates.path("/fallback")
				.and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), hystrixFallbackHandler)
			.andRoute(RequestPredicates.GET("/code")
				.and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), imageCodeHandler);

	}

	// 路由限流 -- IP限流 https://blog.csdn.net/u010889990/article/details/81169328?utm_source=blogxgwz7
	@Bean(value = "ipKeyResolver")
	public KeyResolver ipKeyResolver() {
		return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
	}
}
