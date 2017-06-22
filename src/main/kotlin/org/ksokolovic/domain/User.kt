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

package org.ksokolovic.domain

import org.ksokolovic.dto.UserDTO
import javax.persistence.*

/**
 * @author sokolovic
 */
@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @Column(nullable = false)
    var firstName: String = "",
    @Column(nullable = false)
    var lastName: String = ""
) {
    object UserMapper {
        fun userToUserDTO(user: User) = UserDTO(user.id, user.firstName, user.lastName)

        fun usersToUserDTOs(users: List<User>): List<UserDTO> = users.map { userToUserDTO(it) }

        fun userDTOToUser(userDTO: UserDTO) = User(userDTO.id, userDTO.firstName, userDTO.lastName)

        fun userDTOsToUser(userDTOs: List<UserDTO>): List<User> = userDTOs.map { userDTOToUser(it) }
    }
}