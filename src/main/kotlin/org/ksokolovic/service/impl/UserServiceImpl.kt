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

package org.ksokolovic.service.impl

import org.ksokolovic.domain.User.UserMapper.userToUserDTO
import org.ksokolovic.domain.User.UserMapper.usersToUserDTOs
import org.ksokolovic.dto.UserDTO
import org.ksokolovic.repository.UserRepository
import org.ksokolovic.service.UserService
import org.springframework.stereotype.Service

/**
 * @author sokolovic
 */
@Service
class UserServiceImpl constructor(val repository: UserRepository) : UserService {

    override fun list(): List<UserDTO> {
        return usersToUserDTOs(
            repository.findAllByOrderByFirstNameAscLastNameAsc()
        )
    }

    override fun show(id: Long): UserDTO? {
        val user = repository.findOne(id)
        return when (user) {
            null -> null
            else -> userToUserDTO(user)
        }
    }
}