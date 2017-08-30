const webpack = require('webpack');

module.exports = {
	devtool: 'eval',
    entry: {
        home: './src/main/js/home.js',
    },
    output: {
        filename: '[name].js',
        path: __dirname + '/src/main/resources/static/js'
    },
    module: {
        loaders: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                loaders: ['babel-loader', 'eslint-loader']
            }
        ]
    },
    plugins: [
        new webpack.optimize.CommonsChunkPlugin({
            name: 'vendor',
            filename: 'vendor.js',
            minChunks: 2
        })
    ]
};
