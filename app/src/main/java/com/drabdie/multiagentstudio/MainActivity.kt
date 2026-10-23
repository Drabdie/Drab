package com.drabdie.multiagentstudio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
data class Agent(val name:String,val role:String,val status:String)

class MainActivity: ComponentActivity(){ override fun onCreate(savedInstanceState:Bundle?){ super.onCreate(savedInstanceState); setContent{ StudioApp() } } }

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun StudioApp(){
    MaterialTheme { var selected by remember{mutableIntStateOf(0)}; val agents=listOf(Agent("Atlas","Project Manager","Online"),Agent("Scout","Researcher","Online"),Agent("Scribe","Writer","Ready"))
        Scaffold(topBar={TopAppBar(title={Text("MultiAgent Studio")})},bottomBar={NavigationBar{listOf("Projects","Agents","Activity").forEachIndexed{i,t->NavigationBarItem(selected==i,onClick={selected=i},icon={},label={Text(t)})}}}){p-> when(selected){0->Home(agents,p);1->Agents(agents,p);else->Activity(p)} }
    }
}
@Composable fun Home(agents:List<Agent>,p:PaddingValues){ Column(Modifier.padding(p).padding(16.dp).fillMaxSize(),verticalArrangement=Arrangement.spacedBy(14.dp)){ Text("AI Team Workspace",style=MaterialTheme.typography.headlineMedium); Text("Команда сама распределяет задачи между агентами."); Card{Column(Modifier.padding(16.dp)){Text("Project: Launch campaign",style=MaterialTheme.typography.titleLarge);Text("3 agents • shared memory • audit enabled");Spacer(Modifier.height(12.dp));Button(onClick={}){Text("Start team")}}}; Text("Active agents",style=MaterialTheme.typography.titleMedium);agents.forEach{a->ListItem(headlineContent={Text(a.name)},supportingContent={Text("${a.role} • ${a.status}")})} } }
@Composable fun Agents(agents:List<Agent>,p:PaddingValues){LazyColumn(Modifier.padding(p).padding(12.dp),verticalArrangement=Arrangement.spacedBy(8.dp)){items(agents){a->Card{ListItem(headlineContent={Text(a.name)},supportingContent={Text(a.role)},trailingContent={AssistChip(onClick={},label={Text(a.status)})})}}} }
@Composable fun Activity(p:PaddingValues){Column(Modifier.padding(p).padding(16.dp),verticalArrangement=Arrangement.spacedBy(12.dp)){Text("Activity & approvals",style=MaterialTheme.typography.headlineSmall);Text("✓ Atlas created task: Market research");Text("✓ Scout completed research");Text("⏳ Scribe waiting for approval to publish");Button(onClick={}){Text("Open approvals")}}}
