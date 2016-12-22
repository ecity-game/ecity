'use strict';

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

define(['react', 'superagent', '../settings'], function (React, Superagent, Settings) {

    return React.createClass({

        displayName: 'Register',

        getInitialState: function getInitialState() {
            return {
                login: '',
                password: '',
                email: '',
                name: '',
                surname: '',
                city: '',
                warningMessage: ''
            };
        },

        onInputChange: function onInputChange(target, event) {
            var value = event.target.value;

            this.setState(_defineProperty({}, target, value));
        },

        emailValidation: function emailValidation(email) {
            var atpos = email.indexOf("@");
            var dotpos = email.lastIndexOf(".") || false;
            if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= email.length || dotpos === false) {
                this.setState({
                    email: false,
                    warningMessage: 'Недействительный email'
                });
            } else {
                return email;
            }
        },

        onButtonClick: function onButtonClick(event) {
            var _this = this;

            var login = this.state.login,
                password = this.state.password,
                email = this.emailValidation(this.state.email),
                name = this.state.name.slice(0, 1).toUpperCase() + this.state.name.slice(1),
                surname = this.state.surname.slice(0, 1).toUpperCase() + this.state.surname.slice(1),
                city = this.state.city.slice(0, 1).toUpperCase() + this.state.city.slice(1),
                state = {};

            if (!login || !password) {
                state.warningMessage = 'Заполните все обязательные поля';
            } else if (login && password && email) {

                Superagent.post(Settings.host + Settings.api + '/register').set('Accept', 'application/json').type('form').send({
                    login: login,
                    password: password,
                    email: email,
                    firstName: name,
                    lastName: surname,
                    cityLive: city
                }).end(function (error, response) {
                    console.log(error, response);
                    switch (response.body.code) {
                        case 31:
                            location.href = '#/login';
                            console.log(31);
                            break;
                        case 32:
                            console.log(32);
                            state.warningMessage = 'Игрок с таким именем уже существует';
                            break;
                        default:
                            break;
                    }
                    _this.setState(state);
                });
            } else {
                console.log('not ok');
            }
            this.setState(state);
        },

        render: function render() {
            return React.createElement(
                'div',
                null,
                React.createElement(
                    'div',
                    null,
                    React.createElement('img', { src: '/img/bg.png', className: 'bg-layer-1', alt: '' })
                ),
                React.createElement(
                    'div',
                    { className: 'register bg-color' },
                    React.createElement(
                        'div',
                        { className: 'logo' },
                        React.createElement(
                            'p',
                            null,
                            'E-City'
                        )
                    ),
                    React.createElement(
                        'div',
                        null,
                        React.createElement('input', { type: 'text', className: 'register_input_style', placeholder: '\u041B\u043E\u0433\u0438\u043D', onChange: this.onInputChange.bind(this, 'login') })
                    ),
                    React.createElement(
                        'div',
                        null,
                        React.createElement('input', { type: 'password', className: 'register_input_style', placeholder: '\u041F\u0430\u0440\u043E\u043B\u044C', onChange: this.onInputChange.bind(this, 'password') })
                    ),
                    React.createElement(
                        'div',
                        null,
                        React.createElement('input', { type: 'email', className: 'register_input_style', placeholder: 'Email', onChange: this.onInputChange.bind(this, 'email') })
                    ),
                    React.createElement(
                        'div',
                        null,
                        React.createElement('input', { type: 'text', className: 'register_input_style', placeholder: '\u0418\u043C\u044F(\u043E\u043F\u0446\u0438\u043E\u043D\u0430\u043B\u044C\u043D\u043E)', onChange: this.onInputChange.bind(this, 'name') })
                    ),
                    React.createElement(
                        'div',
                        null,
                        React.createElement('input', { type: 'text', className: 'register_input_style', placeholder: '\u0424\u0430\u043C\u0438\u043B\u0438\u044F(\u043E\u043F\u0446\u0438\u043E\u043D\u0430\u043B\u044C\u043D\u043E)', onChange: this.onInputChange.bind(this, 'surname') })
                    ),
                    React.createElement(
                        'div',
                        null,
                        React.createElement('input', { type: 'text', className: 'register_input_style', placeholder: '\u0413\u043E\u0440\u043E\u0434(\u043E\u043F\u0446\u0438\u043E\u043D\u0430\u043B\u044C\u043D\u043E)', onChange: this.onInputChange.bind(this, 'city') })
                    ),
                    React.createElement(
                        'div',
                        { className: 'warning-message' },
                        this.state.warningMessage
                    ),
                    React.createElement(
                        'div',
                        null,
                        React.createElement('input', { type: 'submit', className: 'register_submit', onClick: this.onButtonClick, value: '\u0417\u0430\u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0438\u0440\u043E\u0432\u0430\u0442\u044C\u0441\u044F' })
                    )
                )
            );
        }
    });
});
//# sourceMappingURL=register.js.map
