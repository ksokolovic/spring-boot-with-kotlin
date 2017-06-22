/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.ksokolovic

import org.ksokolovic.domain.Post
import org.ksokolovic.domain.User
import org.ksokolovic.repository.PostRepository
import org.ksokolovic.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

/**
 * @author sokolovic
 */
@SpringBootApplication
open class SpringWithKotlinApplication {

    @Bean
    open fun init(postRepository: PostRepository, userRepository: UserRepository) = CommandLineRunner {
        postRepository.deleteAll()
        userRepository.deleteAll()

        val johnDoe = userRepository.save(User(null, "John", "Doe"))

        postRepository.save(Post(null, "First post", "This is my first post", johnDoe))
        postRepository.save(Post(null, "Second post", "This is my second post", johnDoe))
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(SpringWithKotlinApplication::class.java, *args);
}