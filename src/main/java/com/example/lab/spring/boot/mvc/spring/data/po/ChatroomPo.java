package com.example.lab.spring.boot.mvc.spring.data.po;

import com.example.lab.spring.boot.mvc.app.domain.ChatroomStatus;
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
@Table(name = "CHATROOM")
public class ChatroomPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private ChatroomStatus status;

    @Column(name = "CREATOR_ID", nullable = false, updatable = false)
    private Integer creatorId;

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
        ChatroomPo chatroomPo = (ChatroomPo) o;
        return getId() != null && Objects.equals(getId(), chatroomPo.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
