var store = {
    state: {
        user: get('user')
    },
    login(user) {
        this.state.user = user;
        set('user', user);
    },
    logout() {
        remove('user');
    },
    getUser(){
        return get('user')
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