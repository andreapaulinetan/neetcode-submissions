const { Client } = require("@notionhq/client");
const fs = require("fs");
const path = require("path");

const notion = new Client({
  auth: process.env.NOTION_TOKEN,
});

const DATABASE_ID = process.env.NOTION_DATABASE_ID;

function prettify(name) {
  return name
    .replace(/([a-z])([A-Z])/g, "$1 $2")
    .replace(/^./, c => c.toUpperCase());
}

async function createEntry(problemName, topic, githubUrl) {
  await notion.pages.create({
    parent: {
      database_id: DATABASE_ID,
    },
    properties: {
      Name: {
        title: [
          {
            text: {
              content: problemName,
            },
          },
        ],
      },

      Language: {
        select: {
          name: "Java",
        },
      },

      Platform: {
        select: {
          name: "NeetCode",
        },
      },

      Topic: {
        multi_select: [
          { name: topic },
          { name: "Data Structures & Algorithms" }
        ],
      },

      "GitHub Url": {
        url: githubUrl,
      },
    },
  });
}

async function main() {
  const baseDir = "Data Structures & Algorithms";

  if (!fs.existsSync(baseDir)) {
    return;
  }

  const folders = fs.readdirSync(baseDir);

  for (const folder of folders) {
    const folderPath = path.join(baseDir, folder);

    if (!fs.statSync(folderPath).isDirectory()) continue;

    const problemName = prettify(folder);

    const githubUrl =
      `https://github.com/${process.env.GITHUB_REPOSITORY}/tree/main/` +
      encodeURIComponent(baseDir) +
      "/" +
      encodeURIComponent(folder);

    await createEntry(
      problemName,
      problemName,
      githubUrl
    );

    console.log(`Created: ${problemName}`);
  }
}

main();
