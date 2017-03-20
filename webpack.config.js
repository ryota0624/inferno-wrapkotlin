/**
 * Created by ryota on 2017/03/22.
 */

const path = require('path');
module.exports = {
    entry: {
        app: path.resolve(__dirname, 'src', 'bundleInferno', 'index.js')
    },
    output: {
        filename: '[name].bundle.js',
        publicPath: '/build/',
        path: path.resolve(__dirname, 'build')
    },
    devtool: 'source-map',
    module: {
        rules: [
            {
                test: /\.ts|\.tsx/,
                use: [
                    { loader: 'ts-loader'}
                ],
                exclude: /node_modules/
            }
        ]
    },
    plugins: [],
    resolve: {
        extensions: [
            ".js", ".ts", ".tsx"
        ]
    },
    devServer: {
        contentBase: [path.join(__dirname, 'public')],
        watchContentBase: true,
        publicPath: '/build/',
        compress: true,
        port: 9000
    }
};