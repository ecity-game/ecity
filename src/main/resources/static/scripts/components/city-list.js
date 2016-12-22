'use strict';

define(['react'], function (React) {

    return React.createClass({

        displayName: 'CityList',

        getInitialState: function getInitialState() {
            return {
                expanded: null
            };
        },

        onClickButton: function onClickButton(city, event) {
            this.setState({
                expanded: this.state.expanded === city.id ? null : city.id
            });
        },

        render: function render() {
            var _this = this;

            return React.createElement(
                'div',
                { className: 'city-list' },
                React.createElement(
                    'h2',
                    null,
                    '\u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435 \u0433\u043E\u0440\u043E\u0434\u043E\u0432'
                ),
                React.createElement(
                    'ul',
                    { className: 'ul-city' },
                    this.props.cities && this.props.cities.map(function (city, i) {

                        return React.createElement(
                            'li',
                            { key: city.id, onClick: _this.onClickButton.bind(_this, city) },
                            React.createElement(
                                'p',
                                null,
                                city.name
                            ),
                            _this.state.expanded === city.id ? React.createElement(
                                'div',
                                null,
                                React.createElement(
                                    'a',
                                    { href: city.url, target: '_blank' },
                                    city.url
                                )
                            ) : null
                        );
                    })
                )
            );
        }

    });
});
//# sourceMappingURL=city-list.js.map
