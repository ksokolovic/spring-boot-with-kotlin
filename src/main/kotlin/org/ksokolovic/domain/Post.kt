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

import org.ksokolovic.dto.PostDTO
import javax.persistence.*

/**
 * @author sokolovic
 */
@Entity
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var title: String = "",
    @Column(columnDefinition = "text")
    var content: String = "",
    @OneToOne(targetEntity = User::class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id", foreignKey = ForeignKey(name = "FK_CREATED_BY"))
    var createdBy: User
) {
    object PostMapper {
        fun postToPostDTO(post: Post) = PostDTO(post.id, post.title, post.content, User.UserMapper.userToUserDTO(post.createdBy))

        fun postsToPostDTOs(posts: List<Post>): List<PostDTO> = posts.map { postToPostDTO(it) }

        fun postDTOToPost(postDTO: PostDTO) = Post(postDTO.id, postDTO.title, postDTO.content, User.UserMapper.userDTOToUser(postDTO.createdBy))

        fun postDTOsToPosts(postDTOs: List<PostDTO>): List<Post> = postDTOs.map { postDTOToPost(it) }
    }
}
