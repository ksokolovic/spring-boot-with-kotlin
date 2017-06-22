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

package org.ksokolovic.api

import org.ksokolovic.dto.UserDTO
import org.ksokolovic.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * @author sokolovic
 */
@RestController
@RequestMapping("/users")
class UserController constructor(val userService: UserService) {

    @RequestMapping(
        method = arrayOf(RequestMethod.GET)
    )
    fun listAll(): ResponseEntity<List<UserDTO>> {
        return ResponseEntity(
            userService.list(), HttpStatus.OK
        )
    }

    @RequestMapping(
        value = "/{id}",
        method = arrayOf(RequestMethod.GET)
    )
    fun show(@PathVariable("id") id: Long): ResponseEntity<UserDTO> {
        val result: UserDTO? = userService.show(id)
        return when (result) {
            null -> ResponseEntity(HttpStatus.NOT_FOUND)
            else -> ResponseEntity(result, HttpStatus.OK)
        }
    }

}