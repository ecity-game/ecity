'use strict';

define(['react', 'superagent', '../settings'], function (React, Superagent, Settings) {

    var alphabet = ['а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ы', 'э', 'ю', 'я'];

    return React.createClass({

        displayName: 'City-Library',

        getInitialState: function getInitialState() {
            return {
                library: [],
                expanded: null,
                letter: '',
                cityInfo: null
                /*
                letterStyle: {
                    color: 'black'
                }
                */
            };
        },

        componentDidMount: function componentDidMount() {
            this.getLibrary();
        },

        getLibrary: function getLibrary() {
            var _this = this;

            Superagent.get(Settings.host + Settings.api + '/cities').set('Accept', 'application/json').end(function (error, response) /* arrow function */{
                _this.setState({
                    library: response.body
                });
            });
        },

        onClickButton: function onClickButton(letter, event) {
            this.setState({
                expanded: this.state.expanded === letter ? null : letter,
                letter: letter,
                cityInfo: null
                //letterStyle: this.state.letterStyle.color === 'red' ? {color: 'black'} : {color: 'red'}
            });
        },

        citySort: function citySort() {
            var sorted = {};
            for (var i = 0, x = this.state.library.length; i < x; i++) {
                var char = this.state.library[i].name[0].toLowerCase();
                if (!sorted[char]) {
                    sorted[char] = [this.state.library[i]];
                } else {
                    sorted[char].push(this.state.library[i]);
                }
            }
            return sorted;
        },

        addCities: function addCities(obj) {
            var _this2 = this;

            var letter = this.state.letter;
            var cities = obj[letter];
            return this.state.expanded === letter ? React.createElement(
                'div',
                { key: letter, className: 'table-cities' },
                React.createElement(
                    'ul',
                    null,
                    cities.map(function (city, i) {
                        return React.createElement(
                            'li',
                            { key: city.id },
                            React.createElement(
                                'p',
                                { onClick: _this2.getCityInfo.bind(_this2, city.id) },
                                city.name
                            )
                        );
                    })
                )
            ) : null;
        },

        getCityInfo: function getCityInfo(city) {
            var _this3 = this;

            if (city) {
                Superagent.get(Settings.host + Settings.api + '/city/' + city).set('Accept', 'application/json')
                /*
                                    .query({
                                        id: city
                                    })                
                */
                .end(function (error, response) {
                    console.log(JSON.parse(response.text));
                    _this3.setState({
                        cityInfo: JSON.parse(response.text)
                    });
                });
            }
        },

        render: function render() {
            var _this4 = this;

            var sort = this.citySort();
            return React.createElement(
                'div',
                { className: 'city-library' },
                React.createElement(
                    'ul',
                    { className: 'letters' },
                    alphabet.map(function (letter, i) {
                        var cities = sort[letter];
                        if (cities) {
                            return React.createElement(
                                'li',
                                { key: i },
                                React.createElement(
                                    'h2',
                                    { onClick: _this4.onClickButton.bind(_this4, letter) /* style={this.state.letterStyle} */ },
                                    letter.toUpperCase()
                                )
                            );
                        }
                    })
                ),
                this.addCities(sort),
                this.state.cityInfo ? React.createElement(
                    'div',
                    { key: this.state.cityInfo.id, className: 'city_info' },
                    React.createElement(
                        'h3',
                        null,
                        this.state.cityInfo.name
                    ),
                    React.createElement(
                        'p',
                        null,
                        React.createElement(
                            'strong',
                            null,
                            '\u041D\u0430\u0441\u0435\u043B\u0435\u043D\u0438\u0435: '
                        ),
                        this.state.cityInfo.population
                    ),
                    React.createElement(
                        'span',
                        null,
                        React.createElement(
                            'strong',
                            null,
                            '\u0423\u0437\u043D\u0430\u0442\u044C \u0431\u043E\u043B\u044C\u0448\u0435: '
                        )
                    ),
                    React.createElement(
                        'a',
                        { href: this.state.cityInfo.url, target: '_blank' },
                        this.state.cityInfo.url
                    )
                ) : null
            );
        }

    });
});
//# sourceMappingURL=city-library.js.map
