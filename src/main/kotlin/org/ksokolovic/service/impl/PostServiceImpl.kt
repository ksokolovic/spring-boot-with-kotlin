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

import org.ksokolovic.domain.Post.PostMapper.postToPostDTO
import org.ksokolovic.domain.Post.PostMapper.postsToPostDTOs
import org.ksokolovic.dto.PostDTO
import org.ksokolovic.repository.PostRepository
import org.ksokolovic.service.PostService
import org.springframework.stereotype.Service

/**
 * @author sokolovic
 */
@Service
class PostServiceImpl constructor(val repository: PostRepository) : PostService {

    override fun list(): List<PostDTO> {
        return postsToPostDTOs(
            repository.findAllByOrderByIdDesc()
        )
    }

    override fun show(id: Long): PostDTO? {
        val post = repository.findOne(id)
        return when (post) {
            null -> null
            else -> postToPostDTO(post)
        }
    }

}