var store = {
    state: {
        user: get('user'),
        authorization: get('authorization')
    },
    login(user, authorization) {
        this.state.user = user;
        set('user', user);
        set('authorization', authorization);
    },
    logout() {
        remove('user');
    },
    getUser() {
        return get('user');
    },
    getAuthorization() {
        return get('authorization');
    }
} 

function get(key) {
    var jsonObject = window.localStorage.getItem(key);
    var jsonString = JSON.parse(jsonObject);
    return jsonString;
}

function set(key, value) {
    var jsonString = JSON.stringify(value);
    window.localStorage.setItem(key, jsonString);
}

function remove(key) {
    window.localStorage.removeItem(key);
}

export default store;