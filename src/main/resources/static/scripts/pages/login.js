'use strict';

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

define(['react', 'superagent', '../settings'], function (React, Superagent, Settings) {

    return React.createClass({

        displayName: 'Login',

        getInitialState: function getInitialState() {
            return {
                login: null,
                password: null
            };
        },

        onInputChange: function onInputChange(target, event) {
            var value = event.target.value;

            this.setState(_defineProperty({}, target, value));
        },

        onButtonClick: function onButtonClick(event) {
            var user = this.state.login;
            var password = this.state.password;

            /*
                        var user = 'user2';
                        var password = 'password1';
            */

            if (user && password) {
                this.props.game.logIn(user, password).then(function () {
                    location.href = '#/before-start';
                    console.log('ok');
                }).fail(function () {
                    console.log('not ok');
                });
            }
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
                    { className: 'login bg-color' },
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
                        React.createElement('input', { type: 'text', className: 'login_input_style', placeholder: '\u041B\u043E\u0433\u0438\u043D', onChange: this.onInputChange.bind(this, 'login') })
                    ),
                    React.createElement(
                        'div',
                        null,
                        React.createElement('input', { type: 'password', className: 'login_input_style', placeholder: '\u041F\u0430\u0440\u043E\u043B\u044C', onChange: this.onInputChange.bind(this, 'password') })
                    ),
                    React.createElement(
                        'div',
                        { className: 'checkbox' },
                        React.createElement('input', { type: 'checkbox' }),
                        '\u0417\u0430\u043F\u043E\u043C\u043D\u0438\u0442\u044C \u043C\u0435\u043D\u044F'
                    ),
                    React.createElement(
                        'div',
                        { className: 'login_register' },
                        React.createElement(
                            'a',
                            { href: '#/register', className: 'text-control-color' },
                            '\u0417\u0430\u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0438\u0440\u043E\u0432\u0430\u0442\u044C\u0441\u044F'
                        )
                    ),
                    React.createElement(
                        'div',
                        { className: 'vorgetPassword' },
                        React.createElement(
                            'a',
                            { href: '#', className: 'text-control-color' },
                            '\u0417\u0430\u0431\u044B\u043B\u0438 \u043F\u0430\u0440\u043E\u043B\u044C?'
                        )
                    ),
                    React.createElement(
                        'div',
                        null,
                        React.createElement('input', { type: 'submit', className: 'login_submit', onClick: this.onButtonClick, value: '\u0412\u043E\u0439\u0442\u0438' })
                    ),
                    React.createElement(
                        'div',
                        { className: 'social text-control-color' },
                        React.createElement(
                            'p',
                            null,
                            '\u0412\u043E\u0439\u0442\u0438 \u043F\u0440\u0438 \u043F\u043E\u043C\u043E\u0449\u0438'
                        ),
                        React.createElement(
                            'div',
                            null,
                            React.createElement('input', { type: 'image', src: '../img/vk.png' }),
                            React.createElement('input', { type: 'image', src: '../img/facebook.png' }),
                            React.createElement('input', { type: 'image', src: '../img/insta.png' })
                        )
                    )
                )
            );
        }
    });
});
//# sourceMappingURL=login.js.map
