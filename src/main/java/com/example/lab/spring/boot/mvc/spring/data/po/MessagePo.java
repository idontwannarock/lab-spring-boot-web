package com.example.lab.spring.boot.mvc.spring.data.po;

import com.example.lab.spring.boot.mvc.app.domain.MessageStatus;
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
@Table(name = "MESSAGE")
public class MessagePo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "CHATROOM_ID", nullable = false)
    private Long chatroomId;

    private String content;

    @NotNull
    private MessageStatus status;

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
        MessagePo messagePo = (MessagePo) o;
        return getId() != null && Objects.equals(getId(), messagePo.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
