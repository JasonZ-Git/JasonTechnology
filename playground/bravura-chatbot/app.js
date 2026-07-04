const restify = require('restify');
const {
    ActivityHandler,
    CloudAdapter,
    ConfigurationBotFrameworkAuthentication
} = require('botbuilder');

// Setup Restify Server
const server = restify.createServer();
server.listen(process.env.port || process.env.PORT || 3978, function () {
   console.log('%s listening to %s', server.name, server.url); 
});

const botFrameworkAuthentication = new ConfigurationBotFrameworkAuthentication(process.env);
const adapter = new CloudAdapter(botFrameworkAuthentication);

adapter.onTurnError = async function (context, error) {
    console.error(error);
    await context.sendActivity('The bot encountered an error.');
};

class EchoBot extends ActivityHandler {
    constructor() {
        super();
        this.onMessage(async function (context, next) {
            await context.sendActivity(`You said: ${context.activity.text}`);
            await next();
        });
    }
}

const bot = new EchoBot();

server.post('/api/messages', async function (req, res) {
    await adapter.process(req, res, async function (context) {
        await bot.run(context);
    });
});
