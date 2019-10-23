// /*
//  *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
//  *  <p>
//  *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
//  *  you may not use this file except in compliance with the License.
//  *  You may obtain a copy of the License at
//  *  <p>
//  * https://www.gnu.org/licenses/lgpl.html
//  *  <p>
//  * Unless required by applicable law or agreed to in writing, software
//  * distributed under the License is distributed on an "AS IS" BASIS,
//  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  * See the License for the specific language governing permissions and
//  * limitations under the License.
//  */
// package store.zabbix.eureka.security;
//
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
// /**
//  * description: 权限放行类
//  *
//  * @author eyck.cui update: 2019/10/23 18:33
//  **/
// @EnableWebSecurity
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.csrf().disable()
//                 .authorizeRequests()
//                 .antMatchers("/actuator/**").permitAll()
//                 .anyRequest()
//                 .authenticated().and().httpBasic();
//     }
// }
