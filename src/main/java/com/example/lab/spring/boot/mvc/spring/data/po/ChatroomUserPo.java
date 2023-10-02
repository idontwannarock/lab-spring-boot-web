package com.example.lab.spring.boot.mvc.spring.data.po;

import com.example.lab.spring.boot.mvc.app.domain.ChatroomUserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "CHATROOM_USER")
public class ChatroomUserPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "USER_ID")
    private Integer userId;

    @NotNull
    @Column(name = "CHATROOM_ID")
    private Long chatroomId;

    @NotNull
    private ChatroomUserRole role;

    @CreationTimestamp
    @Column(name = "CREATE_TIME", nullable = false, updatable = false)
    private Instant createTime;

    @SuppressWarnings("java:S2097")
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ChatroomUserPo chatroomUserPo = (ChatroomUserPo) o;
        return getId() != null && Objects.equals(getId(), chatroomUserPo.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
