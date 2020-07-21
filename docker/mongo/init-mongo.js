db.getSiblingDB('admin')
    .createUser({
        user: 'user',
        pwd: 'user',
        roles: ['readWrite']
});