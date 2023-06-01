# Java Spring Boot backend for ASOI Automation Department

## Разработка базы данных и соответствующих entity
1. UserEntity:
    - id (UUID)
    - email (unique, not null, String)
    - username (unique, not null, String)
    - hashedPassword (not null, String)
2. RoleEntity:
    - id (UUID)
    - roleName (unique, not null, String)
3. GroupEntity:
    - id (UUID)
    - groupName (unique, not null, String)

## Описание взамоотношений Entity
 - UserEntity -> RoleEntity: ManyToOne
 - UserEntity -> GroupEntity: ManyToMany

## Разработка REST API endpoint'ов
Базовый адрес для backend:
 - http://localhost:8080/api/v1
### CRUD для сущностей ролей пользователя
 - [GET] /roles - получение всех ролей пользователя
 - [GET] /roles/{roleId} - получние роли по ID
 - [POST] /roles - добавление новой роли